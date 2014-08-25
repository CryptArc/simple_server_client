//
//  DNViewController.m
//  QuoteIOSClient
//
//  Created by Dima Nemets on 8/24/14.
//  Copyright (c) 2014 ___FULLUSERNAME___. All rights reserved.
//

#import "DNViewController.h"

@interface DNViewController ()

@end

@implementation DNViewController

NSString *response;
NSMutableArray *quotesArray;

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    _tableView.dataSource = self;
    _tableView.delegate = self;
    
    quotesArray = [NSMutableArray new];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

//tableView protocol implementation functions

-(int)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [quotesArray count];
}

- (UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *simpleTableIdentifier = @"QuoteCell";
    UITableViewCell *thisCell = [tableView dequeueReusableCellWithIdentifier:simpleTableIdentifier];
    
    if (thisCell == nil)
    {
        thisCell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:simpleTableIdentifier];
    }
    
    thisCell.textLabel.text = [quotesArray objectAtIndex:indexPath.row];
    
    return thisCell;

}

NSString * const QUOTE_SERVER_URL = @"http://dimanememets1986-serverapp.nodejitsu.com/quote";

- (IBAction)buttonPressed:(UIButton *)sender
{
    //show web activity is going on
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
    
    NSURL *url = [NSURL URLWithString:@"http://dimanememets1986-serverapp.nodejitsu.com/quote"];
    
    NSURLRequest *urlRequest = [NSURLRequest requestWithURL:url];
    
    NSURLConnection *urlConnection = [[NSURLConnection alloc] initWithRequest:urlRequest delegate:self];
    
    if (urlConnection)
    {
        //Connection ok
    }
    
    else
    {
        //ERROR
    }
    
    //implement the protocol: NSURLConnectionDataDelegate Protocol Reference
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    response = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection
{
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
    [quotesArray addObject:response];
    [_tableView reloadData];
}

- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{
    UIAlertView *errorView = [[UIAlertView alloc] initWithTitle:@"Error" message:@"The download could not complete, verify WIFI/3G connection" delegate:nil cancelButtonTitle:@"Dismiss" otherButtonTitles:nil];
    [errorView show];
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
}


@end

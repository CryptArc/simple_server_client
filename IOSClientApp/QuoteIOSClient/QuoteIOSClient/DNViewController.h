//
//  DNViewController.h
//  QuoteIOSClient
//
//  Created by Dima Nemets on 8/24/14.
//  Copyright (c) 2014 ___FULLUSERNAME___. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DNViewController : UIViewController<UITableViewDelegate, UITableViewDataSource>

@property (strong, nonatomic) IBOutlet UITableView *tableView;

- (IBAction)buttonPressed:(UIButton *)sender;

@end
